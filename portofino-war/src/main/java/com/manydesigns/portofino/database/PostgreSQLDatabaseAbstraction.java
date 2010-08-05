/*
 * Copyright (C) 2005-2010 ManyDesigns srl.  All rights reserved.
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

package com.manydesigns.portofino.database;

import java.sql.SQLException;
import java.text.MessageFormat;

/*
* @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
* @author Angelo Lupo          - angelo.lupo@manydesigns.com
* @author Giampiero Granatella - giampiero.granatella@manydesigns.com
*/
public class PostgreSQLDatabaseAbstraction extends CommonDatabaseAbstraction {
    public static final String copyright =
            "Copyright (c) 2005-2010, ManyDesigns srl";
    
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    public PostgreSQLDatabaseAbstraction(ConnectionProvider connectionProvider)
            throws SQLException {
        super(connectionProvider);
    }

    //--------------------------------------------------------------------------
    // Implementation of DatabaseAbstraction
    //--------------------------------------------------------------------------

    public String getConnectionString(String host, int port, String dbName,
            String login, String password) {
        return MessageFormat.format("jdbc:postgresql://{0}:{1}/{2}",
                host, port, dbName);
    }

    public String getDriverClassName() {
        return DRIVER_CLASS_NAME;
    }
}
