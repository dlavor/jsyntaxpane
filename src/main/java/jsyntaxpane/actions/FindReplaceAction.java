/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License
 *       at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jsyntaxpane.actions;

import jsyntaxpane.SyntaxDocument;

import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

/**
 * Finder class.  This class contains the general Find, Find Next,
 * Find Previous, and the Find Marker Actions.
 * <p>
 * Note that all Actions are subclasses of this class because all actions
 * require the find text to be shared among them.  This is the best approach
 * to have all Action classes share this same data.
 *
 * @author Ayman Al-Sairafi
 */
public class FindReplaceAction extends DefaultSyntaxAction {

    public FindReplaceAction() {
        super("FIND_REPLACE");
    }

    @Override
    public void actionPerformed(JTextComponent target, SyntaxDocument sdoc,
                                int dot, ActionEvent e) {
        DocumentSearchData dsd = DocumentSearchData.getFromEditor(target);
        dsd.showReplaceDialog(target);
    }
}