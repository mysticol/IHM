/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: WikittyPublicationAction.java 650 2010-12-23 11:44:57Z sletellier $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/WikittyPublicationAction.java $
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

/**
 * each action can be used by multiple thread
 *
 * @author poussin
 * @version $Revision: 650 $
 *
 * Last update: $Date: 2010-12-23 12:44:57 +0100 (jeu., 23 déc. 2010) $
 * by : $Author: sletellier $
 */
public interface WikittyPublicationAction {
    public String getMapping();
    public void setMapping(String mapping);
    public Object doAction(WikittyPublicationContext context);
}
