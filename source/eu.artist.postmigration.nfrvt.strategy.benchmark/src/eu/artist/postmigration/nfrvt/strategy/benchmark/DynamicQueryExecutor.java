/*******************************************************************************
 * Copyright (c) 2015 ICCS
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
 * Aliki Copaneli, ICCS: Initial API
 * Martin Fleck, TUWIEN: setup default properties in case file can not be found
 * 
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 */
package eu.artist.postmigration.nfrvt.strategy.benchmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DynamicQueryExecutor {
		
	protected String metric;
	protected String provider;
	protected String test;
	protected String workload;
	
	public DynamicQueryExecutor(String providerName, String metric, String test, String workload){
		this.provider = providerName;
		this.metric = metric;
		this.test = test;
		this.workload = workload;
	}
	
	public synchronized String getTest() {
		return test;
	}

	public synchronized void setTest(String test) {
		this.test = test;
	}

	public synchronized String getWorkload() {
		return workload;
	}

	public synchronized void setWorkload(String workload) {
		this.workload = workload;
	}

	public synchronized String getMetric() {
		return metric;
	}

	public synchronized void setMetric(String metric) {
		this.metric = metric;
	}
	
	public synchronized String getProvider() {
		return provider;
	}

	public synchronized void setProvider(String provider) {
		this.provider = provider;
	}

	public Map<String, Double> setupAndExecuteQuery() throws Exception {
		String query;
		try {
			if (workload.equals("ALL")){
				query="Select avg("+metric+"), InstanceType from test."+test+" WHERE CloudProvider='"+provider+"' group by InstanceType;";
			} else{
				query="Select avg("+metric+"), InstanceType from test."+test+" WHERE CloudProvider='"+provider+"' AND Workload LIKE '%"+workload+"%', group by InstanceType;";
			}
//			System.out.println("Query syntax:"+query);
			return runQuery(query);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	public static Map<String, Double> runQuery(String sqlquery) throws Exception {	
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		Class.forName("com.mysql.jdbc.Driver");
		Properties connectionProperties = BenchmarkConstants.getProperties();
		String ip = connectionProperties.getProperty("SQLdatabaseIP");
		String user = connectionProperties.getProperty("SQLDBuser");
		String password = connectionProperties.getProperty("SQLDBKey");
		
		String url = "jdbc:mysql://" +ip + ":3306/test";
		
		Map<String, Double> response = new HashMap<String, Double>();
		
		con = DriverManager.getConnection(url, user, password);
		st = con.createStatement();
		st.setQueryTimeout(2000);
		rs = st.executeQuery(sqlquery);
		while (rs.next()) {
			response.put(rs.getString(2), Double.parseDouble(rs.getString(1)));
		}
		return response;
	}
}
