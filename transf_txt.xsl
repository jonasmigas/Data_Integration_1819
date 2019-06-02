<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" indent="yes" />
	
	<xsl:template match="paises">
		
<TOP_5_mais_populacao>
		      
<xsl:apply-templates select="pais">
	<xsl:sort select="capital" order="descending"/>
</xsl:apply-templates>
*</TOP_5_mais_populacao>
	</xsl:template>
	<xsl:template match="pais">_________
	***<xsl:value-of select="@iso"/>***
	</xsl:template>
</xsl:stylesheet>


