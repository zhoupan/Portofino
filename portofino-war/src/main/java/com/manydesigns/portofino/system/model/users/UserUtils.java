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
package com.manydesigns.portofino.system.model.users;

import com.manydesigns.portofino.context.Application;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/*
* @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
* @author Angelo Lupo          - angelo.lupo@manydesigns.com
* @author Giampiero Granatella - giampiero.granatella@manydesigns.com
* @author Alessio Stalla       - alessio.stalla@manydesigns.com
*/
public class UserUtils {
    public static final String copyright
            = "Copyright (c) 2005-2011, ManyDesigns srl";

    public static final String USERTABLE = "portofino.public.users";
    public static final String GROUPTABLE = "portofino.public.groups";

    public static final String PASSWORD = "pwd";
    public static final String GROUPS = "groups";

    public static final int ACTIVE = 1;
    public static final int SUSPENDED = 2;
    public static final int BANNED = 3;
    public static final int SELFREGITRED = 4;

    public static List<String> manageGroups(Application application, String userId) {
        List<String> groups = new ArrayList<String>();
        if (userId == null) {
            groups.add(Group.ANONYMOUS_GROUP.getName());
        } else {
            User u = (User) application.getObjectByPk(UserUtils.USERTABLE,
                    new User(userId));
            groups.add(Group.ANONYMOUS_GROUP.getName());
            groups.add(Group.REGISTERED_GROUP.getName());

            for (UsersGroups ug : u.getGroups()) {
                if (ug.getDeletionDate() == null) {
                    groups.add(ug.getGroup().getName());
                }
            }
        }
        return groups;
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes("UTF-8"));
            byte raw[] = md.digest();
            return (new BASE64Encoder()).encode(raw);
        } catch (Exception e) {
            throw new Error(e);
        }
    }


}
