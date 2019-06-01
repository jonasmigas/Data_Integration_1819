<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />
	
	<xsl:template match="paises">
		<html><body><table border="1">
		<tr><th>ISO</th><th>Bandeira</th></tr>
		 <xsl:apply-templates select="pais"/>
		</table></body></html>
	</xsl:template>

	<xsl:template match="pais">
		<tr><td><xsl:value-of select="@iso"/></td>
		<td><img src="https:{link_flag}"/>
		</td></tr>
	</xsl:template>
</xsl:stylesheet>


