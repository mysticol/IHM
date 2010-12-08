<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
  <!-- Appel du template: projectteam -->
  <xsl:template match="reservation">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
      <!-- Debut d'ecriture dans le fichier -->
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="16pt" font-weight="bold" space-after="5mm">Reservation pour un Week-end
          </fo:block>
          
          <fo:block font-size="10pt">
            
             <fo:table border="0.5pt solid black" table-layout="fixed">
              <fo:table-column column-width="8cm"/>
              <fo:table-column column-width="10cm"/>
              <fo:table-body>
                <xsl:apply-templates/>
              </fo:table-body>
            </fo:table>
          </fo:block>
          
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
    
    
  </xsl:template>
  <!-- ========================= -->
  <!-- child element: member     -->
  <!-- ========================= -->
  <xsl:template match="infosPerso">
    <fo:table-row height="10mm">
		<fo:table-cell>
	        <fo:block>
	          Nom : <xsl:value-of select="nom"/>
	        </fo:block>
		</fo:table-cell>
		<fo:table-cell>
	        <fo:block>
	          Prenom : <xsl:value-of select="prenom"/>
	        </fo:block>
		</fo:table-cell>
    </fo:table-row>
  </xsl:template>

<xsl:template match="lieux">
	<fo:table-row height="6mm">
		<fo:table-cell>
	        <fo:block>
	          Pays de depart : <xsl:value-of select="paysDepart"/>
	        </fo:block>
		</fo:table-cell>
		<fo:table-cell>
	        <fo:block>
	          Ville de depart : <xsl:value-of select="villeDepart"/>
	        </fo:block>
		</fo:table-cell>
	</fo:table-row>
	<fo:table-row   height="10mm">
		<fo:table-cell>
	        <fo:block>
	          Pays d'arrivee : <xsl:value-of select="paysArrivee"/>
	        </fo:block>
		</fo:table-cell>
		<fo:table-cell>
	        <fo:block>
	          Ville d'arrivee : <xsl:value-of select="villeArrivee"/>
	        </fo:block>
		</fo:table-cell>
	</fo:table-row>
</xsl:template>

  <xsl:template match="manifestation">
    <fo:table-row height="6mm">
		<fo:table-cell>
	        <fo:block>
	          Date de la manifestation : <xsl:value-of select="date"/>
	        </fo:block>
		</fo:table-cell>
	</fo:table-row>
	<fo:table-row height="6mm">
		<fo:table-cell>
	        <fo:block>
	          Manifestation : <xsl:value-of select="manif"/>
	        </fo:block>
		</fo:table-cell>
	</fo:table-row>
	<fo:table-row height="6mm">
		<fo:table-cell>
	        <fo:block>
	          Description de la manifestation :
	        </fo:block>
		</fo:table-cell>
		<fo:table-cell>
	        <fo:block>
	          <xsl:value-of select="description"/>
	        </fo:block>
		</fo:table-cell>
    </fo:table-row>
  </xsl:template>
  
<xsl:template match="infosHotel">
    <fo:table-row height="6mm">
		<fo:table-cell>
	        <fo:block>
	          Nom de l'hotel : <xsl:value-of select="nomHotel"/>
	        </fo:block>
		</fo:table-cell>
    </fo:table-row>
</xsl:template>

<xsl:template match="infosRestaurant">
    <fo:table-row height="6mm">
		<fo:table-cell>
	        <fo:block>
	          Nom du restaurant : <xsl:value-of select="nomRestaurant"/>
	        </fo:block>
		</fo:table-cell>
    </fo:table-row>
</xsl:template>

</xsl:stylesheet>
