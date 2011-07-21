/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: AbstractAction.java 650 2010-12-23 11:44:57Z sletellier $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/AbstractAction.java $
 * %%
 * Copyright (C) 2010 CodeLutin, Benjamin Poussin, Benjamin Poussin
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package unUtils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author poussin
 * @version $Revision: 650 $
 *
 * Last update: $Date: 2010-12-23 12:44:57 +0100 (jeu., 23 déc. 2010) $
 * by : $Author: sletellier $
 */
public abstract class AbstractAction implements WikittyPublicationAction {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(AbstractAction.class);

    protected String mapping = null;

    @Override
    public String getMapping() {
        return mapping;
    }

    @Override
    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

}
