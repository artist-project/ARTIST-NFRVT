/**
 */
package eu.artist.postmigration.nfrvt.lang.gel.gel;

import eu.artist.postmigration.nfrvt.lang.common.artistCommon.ARTISTModel;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Migration Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getName <em>Name</em>}</li>
 *   <li>{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getDate <em>Date</em>}</li>
 *   <li>{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getTransformations <em>Transformations</em>}</li>
 *   <li>{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getPropertyEvaluations <em>Property Evaluations</em>}</li>
 *   <li>{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getEvaluation <em>Evaluation</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage#getMigrationEvaluation()
 * @model
 * @generated
 */
public interface MigrationEvaluation extends ARTISTModel
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage#getMigrationEvaluation_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Date</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Date</em>' attribute.
   * @see #setDate(Date)
   * @see eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage#getMigrationEvaluation_Date()
   * @model
   * @generated
   */
  Date getDate();

  /**
   * Sets the value of the '{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getDate <em>Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Date</em>' attribute.
   * @see #getDate()
   * @generated
   */
  void setDate(Date value);

  /**
   * Returns the value of the '<em><b>Transformations</b></em>' containment reference list.
   * The list contents are of type {@link eu.artist.postmigration.nfrvt.lang.gel.gel.Transformation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transformations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transformations</em>' containment reference list.
   * @see eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage#getMigrationEvaluation_Transformations()
   * @model containment="true"
   * @generated
   */
  EList<Transformation> getTransformations();

  /**
   * Returns the value of the '<em><b>Property Evaluations</b></em>' containment reference list.
   * The list contents are of type {@link eu.artist.postmigration.nfrvt.lang.gel.gel.AppliedPropertyEvaluation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Evaluations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Evaluations</em>' containment reference list.
   * @see eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage#getMigrationEvaluation_PropertyEvaluations()
   * @model containment="true"
   * @generated
   */
  EList<AppliedPropertyEvaluation> getPropertyEvaluations();

  /**
   * Returns the value of the '<em><b>Evaluation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Evaluation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Evaluation</em>' containment reference.
   * @see #setEvaluation(GoalModelEvaluation)
   * @see eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage#getMigrationEvaluation_Evaluation()
   * @model containment="true"
   * @generated
   */
  GoalModelEvaluation getEvaluation();

  /**
   * Sets the value of the '{@link eu.artist.postmigration.nfrvt.lang.gel.gel.MigrationEvaluation#getEvaluation <em>Evaluation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Evaluation</em>' containment reference.
   * @see #getEvaluation()
   * @generated
   */
  void setEvaluation(GoalModelEvaluation value);

} // MigrationEvaluation
