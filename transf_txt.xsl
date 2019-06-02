<?xml version="1.0" encoding="UTF-8" ?>

<!-- New XSLT document created with EditiX XML Editor (http://www.editix.com) at Wed Apr 27 12:24:58 WEST 2016 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="txt" indent="yes" />
	
	<xsl:template match="livros">
		
<listagem>
		      
<xsl:apply-templates select="livro"/>
		
</listagem>	
	</xsl:template>

	<xsl:template match="livro">
		
		      
<xsl:copy-of select="titulo"/>
	</xsl:template>
</xsl:stylesheet>


