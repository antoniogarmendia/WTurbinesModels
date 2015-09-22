/**
 */
package dslComponent;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dslComponent.Vertex#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link dslComponent.Vertex#getIncoming <em>Incoming</em>}</li>
 * </ul>
 * </p>
 *
 * @see dslComponent.dslComponentPackage#getVertex()
 * @model abstract="true"
 * @generated
 */
public interface Vertex extends DocumElt {
	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link dslComponent.Edge}.
	 * It is bidirectional and its opposite is '{@link dslComponent.Edge#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see dslComponent.dslComponentPackage#getVertex_Incoming()
	 * @see dslComponent.Edge#getTarget
	 * @model opposite="target" ordered="false"
	 * @generated
	 */
	EList<Edge> getIncoming();

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
	 * The list contents are of type {@link dslComponent.Edge}.
	 * It is bidirectional and its opposite is '{@link dslComponent.Edge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see dslComponent.dslComponentPackage#getVertex_Outgoing()
	 * @see dslComponent.Edge#getSource
	 * @model opposite="source" ordered="false"
	 * @generated
	 */
	EList<Edge> getOutgoing();

} // Vertex
