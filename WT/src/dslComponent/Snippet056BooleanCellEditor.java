/*******************************************************************************
 * Copyright (c) 2006, 2014 Tom Schindl and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl - initial API and implementation
 *     Jeanderson Candido <http://jeandersonbc.github.io> - Bug 414565
 *******************************************************************************/

package dslComponent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Snippet056BooleanCellEditor {

	public Snippet056BooleanCellEditor(final Shell shell) {

		final TreeViewer v = new TreeViewer(shell, SWT.BORDER
				| SWT.FULL_SELECTION);
		v.getTree().setLinesVisible(true);
		v.getTree().setHeaderVisible(true);

		FocusCellOwnerDrawHighlighter h = new FocusCellOwnerDrawHighlighter(v) {

			@Override
			protected Color getSelectedCellBackgroundColorNoFocus(
					ViewerCell cell) {
				return shell.getDisplay().getSystemColor(
						SWT.COLOR_WIDGET_BACKGROUND);
			}

			@Override
			protected Color getSelectedCellForegroundColorNoFocus(
					ViewerCell cell) {
				return shell.getDisplay().getSystemColor(
						SWT.COLOR_WIDGET_FOREGROUND);
			}
		};

		TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(
				v, h);
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(
				v);

		int feature = ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL
				| ColumnViewerEditor.KEYBOARD_ACTIVATION;

		TreeViewerEditor.create(v, focusCellManager, actSupport, feature);

		final TextCellEditor textCellEditor = new TextCellEditor(v.getTree());
		final CheckboxCellEditor booleanCellEditor = new CheckboxCellEditor(
				v.getTree());

		TreeViewerColumn column = createColumnFor(v, "Column 1");
		column.setLabelProvider(new MyColumnLabelProvider("Column 1"));
		column.setEditingSupport(new MyEditingSupport(v, v, textCellEditor));

		column = createColumnFor(v, "Column 2");
		column.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				return ((MyModel) element).flag + "";
			}

		});
		column.setEditingSupport(new EditingSupport(v) {
			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return booleanCellEditor;
			}

			@Override
			protected Object getValue(Object element) {
				return new Boolean(((MyModel) element).flag);
			}

			@Override
			protected void setValue(Object element, Object value) {
				((MyModel) element).flag = ((Boolean) value).booleanValue();
				v.update(element, null);
			}
		});
		column = createColumnFor(v, "Column 3");
		column.setLabelProvider(new MyColumnLabelProvider("Column 3"));
		column.setEditingSupport(new MyEditingSupport(v, v, textCellEditor));

		v.setContentProvider(new MyContentProvider());
		v.setInput(createModel());
	}

	private TreeViewerColumn createColumnFor(final TreeViewer viewer,
			String label) {
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText(label);
		return column;
	}

	private MyModel createModel() {

		MyModel root = new MyModel(0, null);
		root.counter = 0;

		MyModel tmp;
		MyModel subItem;
		for (int i = 1; i < 10; i++) {
			tmp = new MyModel(i, root);
			root.child.add(tmp);
			for (int j = 1; j < i; j++) {
				subItem = new MyModel(j, tmp);
				subItem.child.add(new MyModel(j * 100, subItem));
				tmp.child.add(subItem);
			}
		}

		return root;
	}

	public static void main(String[] args) {
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		new Snippet056BooleanCellEditor(shell);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
	}

	private final class MyEditingSupport extends EditingSupport {
		private final TreeViewer v;
		private final TextCellEditor textCellEditor;

		private MyEditingSupport(ColumnViewer viewer, TreeViewer v,
				TextCellEditor textCellEditor) {
			super(viewer);
			this.v = v;
			this.textCellEditor = textCellEditor;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return textCellEditor;
		}

		@Override
		protected Object getValue(Object element) {
			return ((MyModel) element).counter + "";
		}

		@Override
		protected void setValue(Object element, Object value) {
			((MyModel) element).counter = Integer.parseInt(value.toString());
			v.update(element, null);
		}
	}

	private final class MyColumnLabelProvider extends ColumnLabelProvider {
		private String prefix;

		public MyColumnLabelProvider(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public String getText(Object element) {
			return this.prefix + " => " + element.toString();
		}
	}

	private class MyContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return ((MyModel) inputElement).child.toArray();
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return getElements(parentElement);
		}

		@Override
		public Object getParent(Object element) {
			if (element == null) {
				return null;
			}
			return ((MyModel) element).parent;
		}

		@Override
		public boolean hasChildren(Object element) {
			return ((MyModel) element).child.size() > 0;
		}

	}

	public class MyModel {

		public MyModel parent;
		public List<MyModel> child = new ArrayList<MyModel>();
		public int counter;
		public boolean flag;

		public MyModel(int counter, MyModel parent) {
			this.parent = parent;
			this.counter = counter;
			this.flag = counter % 2 == 0;
		}

		@Override
		public String toString() {
			String rv = "Item ";
			if (parent != null) {
				rv = parent.toString() + ".";
			}

			rv += counter;

			return rv;
		}
	}

}