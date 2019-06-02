<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" indent="yes" />
	
	<xsl:template match="/">
<TOP_População>*<xsl:text>&#10;</xsl:text><xsl:text>&#10;</xsl:text>
            <xsl:for-each select="paises/pais">
      <xsl:sort select="populacao" data-type="number" order="descending"/>
<xsl:value-of select="concat(' ',@iso,' | ',populacao,' ')"/><xsl:text>&#10;</xsl:text>
      </xsl:for-each>
*</TOP_População>
</xsl:template>
</xsl:stylesheet>
