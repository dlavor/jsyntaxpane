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
package jsyntaxpane.components;

import jsyntaxpane.util.Configuration;

import javax.swing.*;

/**
 * A Component that is installed to the EditorKit to perform GUI operations
 * on the Editor.
 *
 * @author Ayman Al-Sairafi
 */
public interface SyntaxComponent {

    /**
     * Configure the component using the given properties. The keys
     * needed for configuration will be prefixed by the given prefix
     *
     * @param config configuration data
     */
    public void config(Configuration config);

    /**
     * Called to install the component on an editor
     */
    public void install(JEditorPane editor);

    /**
     * Called when the component is to be removed from the editor
     */
    public void deinstall(JEditorPane editor);

    /**
     * The status is used to have proper propertyChange support.  We need to know if we are INSTALLING
     * the component or DE-INSTALLING it
     */
    static enum Status {
        INSTALLING,
        DEINSTALLING
    }
}
