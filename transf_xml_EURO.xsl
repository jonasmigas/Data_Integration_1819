<?xml version="1.0" encoding="UTF-8" ?>

<!-- New XSLT document created with EditiX XML Editor (http://www.editix.com) at Wed Apr 27 12:24:58 WEST 2016 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes" />
	
	<xsl:template match="paises">
		
<PaisesEURO>
		      
<xsl:apply-templates select="pais"/>
		
</PaisesEURO>
	</xsl:template>

	<xsl:template match="pais">
		<xsl:if test="moeda='Euro'">
			<ISO_Pais><xsl:value-of select="@iso"/></ISO_Pais>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>


