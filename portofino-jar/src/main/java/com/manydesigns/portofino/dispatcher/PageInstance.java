/*
 * Copyright (C) 2005-2011 ManyDesigns srl.  All rights reserved.
 * http://www.manydesigns.com/
 *
 * Unless you have purchased a commercial license agreement from ManyDesigns srl,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as published by
 * the Free Software Foundation.
 *
 * There are special exceptions to the terms and conditions of the GPL
 * as it is applied to this software. View the full text of the
 * exception in file OPEN-SOURCE-LICENSE.txt in the directory of this
 * software distribution.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307  USA
 *
 */

package com.manydesigns.portofino.dispatcher;

import com.manydesigns.portofino.actions.PortofinoAction;
import com.manydesigns.portofino.application.Application;
import com.manydesigns.portofino.model.pages.Layout;
import com.manydesigns.portofino.model.pages.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
* @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
* @author Angelo Lupo          - angelo.lupo@manydesigns.com
* @author Giampiero Granatella - giampiero.granatella@manydesigns.com
* @author Alessio Stalla - alessio.stalla@manydesigns.com
*/
public class PageInstance {

    protected final Application application;
    protected final Page page;
    protected final File directory;
    protected final List<String> parameters;
    protected final PageInstance parent;
    protected Object configuration;
    protected Class<PortofinoAction> actionClass;
    protected PortofinoAction actionBean;

    //**************************************************************************
    // Logging
    //**************************************************************************

    public static final Logger logger = LoggerFactory.getLogger(PageInstance.class);

    public PageInstance(PageInstance parent, File directory, Application application, Page page) {
        this.parent = parent;
        this.directory = directory;
        this.application = application;
        this.page = page;
        this.parameters = new ArrayList<String>();
    }

    public Page getPage() {
        return page;
    }

    public Application getApplication() {
        return application;
    }

    //**************************************************************************
    // Utility Methods
    //**************************************************************************

    /*public PageInstance findChildPageByFragment(String fragment) {
        for(PageInstance page : getChildPageInstances()) {
            if(fragment.equals(page.getPage().getFragment())) {
                return page;
            }
        }
        logger.debug("Child page not found: {}", fragment);
        return null;
    }*/

    public String getUrlFragment() {
        String fragment = directory.getName();
        for(String param : parameters) {
            fragment += "/" + param;
        }
        return fragment;
    }

    public File getDirectory() {
        return directory;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public Object getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Object configuration) {
        this.configuration = configuration;
    }

    public void setActionClass(Class<PortofinoAction> actionClass) {
        this.actionClass = actionClass;
    }

    public Class<PortofinoAction> getActionClass() {
        return actionClass;
    }

    public PortofinoAction getActionBean() {
        return actionBean;
    }

    public void setActionBean(PortofinoAction actionBean) {
        this.actionBean = actionBean;
    }

    public PageInstance getParent() {
        return parent;
    }

    public Layout getLayout() {
        if(getParameters().isEmpty()) {
            return getPage().getLayout();
        } else {
            return getPage().getDetailLayout();
        }
    }
}
