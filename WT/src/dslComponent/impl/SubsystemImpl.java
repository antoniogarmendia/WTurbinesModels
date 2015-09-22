/**
 */
package dslComponent.impl;

import dslComponent.Component;
import dslComponent.ControlSubsystem;
import dslComponent.Subsystem;
import dslComponent.dslComponentPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subsystem</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dslComponent.impl.SubsystemImpl#getName <em>Name</em>}</li>
 *   <li>{@link dslComponent.impl.SubsystemImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link dslComponent.impl.SubsystemImpl#getBeh <em>Beh</em>}</li>
 *   <li>{@link dslComponent.impl.SubsystemImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link dslComponent.impl.SubsystemImpl#getSubsystems <em>Subsystems</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubsystemImpl extends EObjectImpl implements Subsystem {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBeh() <em>Beh</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeh()
	 * @generated
	 * @ordered
	 */
	protected EList<ControlSubsystem> beh;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> components;

	/**
	 * The cached value of the '{@link #getSubsystems() <em>Subsystems</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubsystems()
	 * @generated
	 * @ordered
	 */
	protected EList<Subsystem> subsystems;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubsystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return dslComponentPackage.Literals.SUBSYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, dslComponentPackage.SUBSYSTEM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, dslComponentPackage.SUBSYSTEM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ControlSubsystem> getBeh() {
		if (beh == null) {
			beh = new EObjectContainmentEList.Resolving<ControlSubsystem>(ControlSubsystem.class, this, dslComponentPackage.SUBSYSTEM__BEH);
		}
		return beh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Component> getComponents() {
		if (components == null) {
			components = new EObjectContainmentEList.Resolving<Component>(Component.class, this, dslComponentPackage.SUBSYSTEM__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Subsystem> getSubsystems() {
		if (subsystems == null) {
			subsystems = new EObjectContainmentEList.Resolving<Subsystem>(Subsystem.class, this, dslComponentPackage.SUBSYSTEM__SUBSYSTEMS);
		}
		return subsystems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case dslComponentPackage.SUBSYSTEM__BEH:
				return ((InternalEList<?>)getBeh()).basicRemove(otherEnd, msgs);
			case dslComponentPackage.SUBSYSTEM__COMPONENTS:
				return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
			case dslComponentPackage.SUBSYSTEM__SUBSYSTEMS:
				return ((InternalEList<?>)getSubsystems()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case dslComponentPackage.SUBSYSTEM__NAME:
				return getName();
			case dslComponentPackage.SUBSYSTEM__DESCRIPTION:
				return getDescription();
			case dslComponentPackage.SUBSYSTEM__BEH:
				return getBeh();
			case dslComponentPackage.SUBSYSTEM__COMPONENTS:
				return getComponents();
			case dslComponentPackage.SUBSYSTEM__SUBSYSTEMS:
				return getSubsystems();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case dslComponentPackage.SUBSYSTEM__NAME:
				setName((String)newValue);
				return;
			case dslComponentPackage.SUBSYSTEM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case dslComponentPackage.SUBSYSTEM__BEH:
				getBeh().clear();
				getBeh().addAll((Collection<? extends ControlSubsystem>)newValue);
				return;
			case dslComponentPackage.SUBSYSTEM__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends Component>)newValue);
				return;
			case dslComponentPackage.SUBSYSTEM__SUBSYSTEMS:
				getSubsystems().clear();
				getSubsystems().addAll((Collection<? extends Subsystem>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case dslComponentPackage.SUBSYSTEM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case dslComponentPackage.SUBSYSTEM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case dslComponentPackage.SUBSYSTEM__BEH:
				getBeh().clear();
				return;
			case dslComponentPackage.SUBSYSTEM__COMPONENTS:
				getComponents().clear();
				return;
			case dslComponentPackage.SUBSYSTEM__SUBSYSTEMS:
				getSubsystems().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case dslComponentPackage.SUBSYSTEM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case dslComponentPackage.SUBSYSTEM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case dslComponentPackage.SUBSYSTEM__BEH:
				return beh != null && !beh.isEmpty();
			case dslComponentPackage.SUBSYSTEM__COMPONENTS:
				return components != null && !components.isEmpty();
			case dslComponentPackage.SUBSYSTEM__SUBSYSTEMS:
				return subsystems != null && !subsystems.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //SubsystemImpl
