package com.kekanath.sudoku.applet;

import javax.swing.JTextField;
import javax.swing.text.*;

/**
 * An extension of JTextField which only allows positive integer numbers to be
 * entered.
 * <p/>
 * This reduces the validation required on the field after entry, and gives a
 * nicer entry field to the end user - if they cannot even enter invalid values,
 * they will not be plagued by entering data only to have it overrulled by the
 * validating methods.
 * <p/>
 *
 * @author Kannan
 * @version 1.0
 */
public class PositiveIntegerField extends JTextField {
    /**
     * This is a standard JDK generated serial version UID for serialization.
     */
    private static final long serialVersionUID = 5165L;

    /**
     * Constructs a new empty PositiveIntegerField with the zero columns. The
     * default document will be a NumericDocument which only allows positive
     * integer numbers to be entered.
     * <p/>
     */
    public PositiveIntegerField() {
        super();
    }

    /**
     * Constructs a new empty PositiveIntegerField with the specified number of
     * columns. The default document will be a NumericDocument which only allows
     * positive integer numbers to be entered.
     * <p/>
     *
     * @param columns the number of columns to use to calculate the preferred width;
     *                if columns is set to zero, the preferred width will be
     *                whatever naturally results from the component implementation
     */
    public PositiveIntegerField(int columns) {
        super(columns);
    }

    /**
     * Creates the default implementation of the model to be used at
     * construction. An instance of NumericDocument is returned.
     *
     * @return NumericDocument a document which only allows positive integers
     */
    protected Document createDefaultModel() {
        return new NumericDocument();
    }

    /**
     * A document that only allows positive integer numbers to be entered.
     * <p/>
     *
     * @see javax.swing.text.PlainDocument for further implementation issues and
     *      issues with Serialization.
     */
    class NumericDocument extends PlainDocument {
        /**
         * A version number for this class so that serialization can occur
         * without worrying about the underlying class changing between
         * serialization and deserialization.
         */
        private static final long serialVersionUID = 5165L;

        /**
         * Inserts some content into the document. Inserting content causes a
         * write lock to be held while the actual changes are taking place,
         * followed by notification to the observers on the thread that grabbed
         * the write lock.
         * <p/>
         * This method is thread safe, although most Swing methods are not.
         * Please see {@link
         * "http://java.sun.com/products/jfc/swingdoc-archive/threads.html",
         * "Threads and Swing"} for more information.
         *
         * @param offs the starting offset >= 0
         * @param str  the string to insert; does nothing with null/empty strings
         * @param a    the attributes for the inserted content
         * @throws BadLocationException the given insert position is not a valid position within
         *                              the document
         */
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            char[] input = str.toCharArray();
            int result = 0;
            boolean valid = false;

            for (char anInput : input) {
                if (Character.isDigit(anInput)) {
                    valid = true;
                    result = result * 10 + Character.digit(anInput, 10);
                }
            }

            if(getLength() == getColumns()) {
            	//we have reached the threshold limit
            	valid = false;
            }
            if (valid) {
                super.insertString(offs, "" + result, a);
            }
        }
    }
}
