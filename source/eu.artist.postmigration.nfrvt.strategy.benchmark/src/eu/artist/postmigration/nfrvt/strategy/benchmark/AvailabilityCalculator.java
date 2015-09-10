/*******************************************************************************
 * Copyright (c) 2014 ICCS
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * George Kousiouris: Initial API
 * 
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 */
package eu.artist.postmigration.nfrvt.strategy.benchmark;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * @author geo
 * 
 */
public abstract class AvailabilityCalculator {
	
	public static MultipleDCResults calculateAvailability(int DefinedQuantumofTimeInSeconds, int startYear,int startMonth, int startDay, int stopYear,int stopMonth, int stopDay){// (Date thisDate,
		
		double OVERALL_MONTH_INTERVAL_SECONDS=0;

		//
		try {
			Properties propertiesFile = BenchmarkConstants.getProperties();
			String databaseIP = propertiesFile.getProperty("3alibIP"); 
			MultipleDCResults response=new MultipleDCResults();
			Mongo mongoClient;
			
			System.out.println("DB NoSQL:"+databaseIP);
			mongoClient = new Mongo(databaseIP);
			DB db = mongoClient.getDB("3alib");
			System.out.println("Host address:" + databaseIP);
			DBCollection coll = db.getCollection("log_samples");

						
			Date date = new Date();
			Calendar calendarFrom = Calendar.getInstance();
			calendarFrom.setTime(date);
			calendarFrom.set(startYear, startMonth-1, startDay, 0, 0, 0);

			Date dateFrom = calendarFrom.getTime();

			Calendar calendarTo = Calendar.getInstance();
			calendarTo.setTime(date);
			calendarTo.set(stopYear, stopMonth-1, stopDay, 23, 59, 59);

			Date dateTo = calendarTo.getTime();

			System.out.println("Date beginning:" + dateFrom.toString());
			System.out.println("Date ending:" + dateTo.toString());
			ObjectId from = new ObjectId(dateFrom);
			ObjectId to = new ObjectId(dateTo);
			
			List<?> distinctTemplates = coll.distinct("location.parent.id");//distinct("imageId");

			for (int i = 0; i < distinctTemplates.size(); i++) {

				String index="-1";

				System.out.println("Distinct Region IDs:"
						+ distinctTemplates.get(i).toString());


				// query based on date to filter needed month

				BasicDBObject query = new BasicDBObject("_id", new BasicDBObject(
						"$gte", from).append("$lte", to))
				.append("location.parent.id", distinctTemplates.get(i).toString());

				DBCursor cursor = coll.find(query);
				cursor.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
				cursor.batchSize(100);

				try {
					long startID = 0;
					long stopID = 0;
					long diffSeconds = 0;
					double PREDEFINED_LOGICAL_SAMPLE_INTERVAL_IN_SECONDS=500;//interval in which we would logically
					//have at least one sample if the daemon is running

					DBObject thisObject=cursor.next();
					System.out.println("First object:"+thisObject.toString());
					int cursorCount=cursor.count();
					System.out.println("Cursor count:"+cursor.count());
					DBObject previousObject=thisObject;
					int k=0;
					while (k<(cursorCount+1)) {

						if ((k%1000)==0){
							System.out.println("Progress:"+k+" from "+cursorCount+" overall records");
						}

						if (((thisObject.get("reachability")).equals("UNREACHABLE"))&&index.equals("-1")){ //if it is the first unavailable sample
							System.out.println("Changing index to 1...");
							startID=((ObjectId)thisObject.get("_id")).getTime();//this line's id
							System.out.println("StartID is: "+startID);
							index="1";	
						}


						if (((thisObject.get("reachability")).equals("UNREACHABLE"))&&(!(index.equals("-1")))){
							long gapstopID=((ObjectId)thisObject.get("_id")).getTime();
							long gapstartID=((ObjectId)previousObject.get("_id")).getTime();
							long GapdiffSeconds = (gapstopID-gapstartID) / 1000; // 60;
							if (GapdiffSeconds>PREDEFINED_LOGICAL_SAMPLE_INTERVAL_IN_SECONDS){
								System.out.println("Found gap...");

								stopID=((ObjectId)previousObject.get("_id")).getTime();//this line's id to end interval
								System.out.println("StopID is previous: "+stopID);
								diffSeconds = (stopID-startID) / 1000;
								
								if (diffSeconds>DefinedQuantumofTimeInSeconds){
									OVERALL_MONTH_INTERVAL_SECONDS=OVERALL_MONTH_INTERVAL_SECONDS+diffSeconds;
									System.out.println("Overall month interval in seconds now:"+OVERALL_MONTH_INTERVAL_SECONDS);
								}
								startID=((ObjectId)thisObject.get("_id")).getTime();//this line's id

							}else{
								//standard logic to cover generic case of consecutive unavailable samples
							}

						}

						if (((((thisObject.get("reachability")).equals("REACHABLE"))||(!(cursor.hasNext()))))&&(!(index.equals("-1")))){
							if (!(cursor.hasNext())){
								System.out.println("FINAL ELEMENT REACHED");
							}	stopID=((ObjectId)previousObject.get("_id")).getTime();
							diffSeconds = (stopID-startID) / 1000; // 60;
							
							if (diffSeconds>DefinedQuantumofTimeInSeconds){
								OVERALL_MONTH_INTERVAL_SECONDS=OVERALL_MONTH_INTERVAL_SECONDS+diffSeconds;
								System.out.println("Overall month interval in seconds now:"+OVERALL_MONTH_INTERVAL_SECONDS);
							}
							System.out.println("Resetting index to -1...");
							index="-1";
						}
						
						if ((cursor.hasNext())){
						previousObject=thisObject;
						thisObject=cursor.next();
						}
						k++;
					}

					System.out.println("Final Overall month unavailable interval in seconds now:"+OVERALL_MONTH_INTERVAL_SECONDS);
					double OverallUnavailableIntervalInMinutes= OVERALL_MONTH_INTERVAL_SECONDS/60;
					System.out.println("OverallUnavailableIntervalInMinutes:"+OverallUnavailableIntervalInMinutes);
					double OverallIntervalInSeconds = (dateTo.getTime()-dateFrom.getTime()) / 1000; 
					double OverallIntervalInMinutes= OverallIntervalInSeconds/60;
					double finalAvailabilityPercentage=100.0*((OverallIntervalInMinutes-OverallUnavailableIntervalInMinutes)/OverallIntervalInMinutes);
					double downtimeInPercent=100.0-finalAvailabilityPercentage;
					response.DC.add(distinctTemplates.get(i).toString());
					response.availability.add((Double)downtimeInPercent);
					System.out.println("Final percentage of availability based on provider definition in the given interval:"+finalAvailabilityPercentage);
				}
				catch (NoSuchElementException e2){

					System.out.println("No available data for this period...");

				}
				catch (Exception e1){

					e1.printStackTrace();

				} finally {
					cursor.close();
				}

			}
			return response;

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		} catch (MongoException e) {
			System.out.println("No available data for this period...");
			return null;
		} 
	}
	
	public static boolean isDBAvailable() {
		try {
			Properties propertiesFile = BenchmarkConstants.getProperties();
			String databaseIP = propertiesFile.getProperty("3alibIP");
			Mongo mongoClient = new Mongo(databaseIP);
			DB db = mongoClient.getDB("3alib");
			return db != null;
		} catch (MongoException | IOException e) {
			return false;
		}
	}
	
	public static MultipleDCResults calculateCloudSleuthAvailability(int startYear,int startMonth, int startDay, int stopYear,int stopMonth, int stopDay){
		
		try {
			Properties propertiesFile = BenchmarkConstants.getProperties();
			String databaseIP = propertiesFile.getProperty("3alibIP");
			MultipleDCResults response=new MultipleDCResults();
			double CloudSleuthAvailability=0;
			double CloudSleuthDowntime=0;
			Mongo mongoClient;
			mongoClient = new Mongo(databaseIP);
			DB db = mongoClient.getDB("3alib");
			System.out.println("Host address:" + databaseIP);
			DBCollection coll = db.getCollection("log_samples");
			Date date = new Date();
			Calendar calendarFrom = Calendar.getInstance();
			calendarFrom.setTime(date);
			calendarFrom.set(startYear, startMonth-1, startDay, 0, 0, 0);
			Date dateFrom = calendarFrom.getTime();
			Calendar calendarTo = Calendar.getInstance();
			calendarTo.setTime(date);
			calendarTo.set(stopYear, stopMonth-1, stopDay, 23, 59, 59);
			Date dateTo = calendarTo.getTime();
			System.out.println("Date beginning:" + dateFrom.toString());
			System.out.println("Date ending:" + dateTo.toString());
			ObjectId from = new ObjectId(dateFrom);
			ObjectId to = new ObjectId(dateTo);
			List<?> distinctTemplates = coll.distinct("location.parent.id");
		
			for (int i = 0; i < distinctTemplates.size(); i++) {
				System.out.println("Region ID:"
						+ distinctTemplates.get(i).toString());
				BasicDBObject queryPerRegionOverall = new BasicDBObject("_id", new BasicDBObject(
						"$gte", from).append("$lte", to))
				.append("location.parent.id", distinctTemplates.get(i).toString());

				DBCursor cursorOverall;
				cursorOverall= coll.find(queryPerRegionOverall);
				cursorOverall.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
				
				System.out.println("Overall records:"+cursorOverall.length());
				
								
				BasicDBObject queryUnavailable = new BasicDBObject("_id", new BasicDBObject(
						"$gte", from).append("$lte", to))
				.append("location.parent.id", distinctTemplates.get(i).toString()).append("reachability", "UNREACHABLE");

				DBCursor cursorUnavailable = coll.find(queryUnavailable);
				cursorUnavailable.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
				System.out.println("Unavailable records:"+cursorUnavailable.length());
				
				CloudSleuthAvailability=100*(((double)cursorOverall.length()-(double)cursorUnavailable.length())/cursorOverall.length());
				System.out.println("Cloudsleuth based availability:"+CloudSleuthAvailability);
				CloudSleuthDowntime=100.0- CloudSleuthAvailability;
				response.DC.add(distinctTemplates.get(i).toString());
				response.availability.add((Double)CloudSleuthDowntime);
			}
			return response;
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		} 
		catch (NoSuchElementException e){
			System.out.println("No available data for this period...");
			return null;
		} catch (MongoException e) {
			System.out.println("No available data for this period...");
			return null;
		}
	}

}
