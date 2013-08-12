/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import connection.FileInfo;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Oliver
 */
public class DirTreePane extends JPanel{
    
    private JTree tree;
    private String rootParentPath;
    
    public DirTreePane( File fRoot, boolean showFiles ){
        rootParentPath = fRoot.getParent();
        if( rootParentPath == null )
            rootParentPath = "";
        
        //build fully populated tree
        MutableTreeNode rootNode = buildNode( fRoot, showFiles );
        tree = new JTree( rootNode );
        
        //pass property changes up hierarchy
        tree.addPropertyChangeListener( new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                DirTreePane.this.firePropertyChange(null, null, null);
            }
        });
        
        //change render settings if showing only directories
        if( !showFiles ){
            DefaultTreeCellRenderer renderer = 
                new DefaultTreeCellRenderer();
            renderer.setLeafIcon( renderer.getClosedIcon() );
            tree.setCellRenderer(renderer);
        }
        
        //by default, the root node is selected
        tree.setSelectionRow(0);
        
        //add the tree to this panel within a scroll pane
        JScrollPane scroller = new JScrollPane( tree );
        this.setLayout( new BorderLayout() );
        this.add( scroller, BorderLayout.CENTER );
    }
    
    public JTree getTree(){
        return tree;
    }
    
    public String getSelectedPath() throws Exception{
        TreePath sPath = tree.getSelectionPath();
        
        if( sPath == null )
            throw new Exception( "nothing selected" );
        
        String result = rootParentPath;
        for(Object obj : sPath.getPath() ){
            result += File.separator + obj.toString();
        }
        return result;
    }
    
    private MutableTreeNode buildNode( File dir, boolean showFiles ){
        DefaultMutableTreeNode result = new DefaultMutableTreeNode( dir.getName() );
        
        if( dir.isDirectory() ){
            for( File f : dir.listFiles() ){
                if( showFiles || f.isDirectory() ){
                    result.add( buildNode( f, showFiles ) );
                }
            }
        }else
            result.setAllowsChildren( false );
        
        return result;
    }
    
    //<editor-fold desc="private renderer" defaultstate="collapsed">
    private class MyRenderer extends DefaultTreeCellRenderer {
 
        public Component getTreeCellRendererComponent(
                            JTree tree,
                            Object value,
                            boolean sel,
                            boolean expanded,
                            boolean leaf,
                            int row,
                            boolean hasFocus) {
 
            super.getTreeCellRendererComponent(
                            tree, value, sel,
                            expanded, leaf, row,
                            hasFocus);
            if (leaf && representsDirectory(value)) {
                setIcon(getClosedIcon());
            }
 
            return this;
        }
 
        protected boolean representsDirectory(Object value) {
            DefaultMutableTreeNode dmt = new DefaultMutableTreeNode( value );
            if( dmt.getAllowsChildren() )
                return true;
            return false;       
        }
    }
    //</editor-fold>
}