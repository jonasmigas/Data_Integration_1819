<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" indent="yes" />
	
	<xsl:template match="paises">
		
<Top5_Mais_Populacao>
		      
<xsl:apply-templates select="pais">
	<xsl:sort select="populacao" order="descending"/>
</xsl:apply-templates>
*</Top5_Mais_Populacao>
	</xsl:template>
	<xsl:template match="pais"><xsl:text>&#160;</xsl:text><xsl:text>&#xA;</xsl:text>
	<xsl:value-of select="concat(' ',@iso,' | ',populacao,' ')"/>
	</xsl:template>
</xsl:stylesheet>

