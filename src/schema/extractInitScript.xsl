<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:transform
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:beans="http://www.springframework.org/schema/beans"
  xmlns:dellroad-stuff="http://dellroad-stuff.googlecode.com/schema/dellroad-stuff"
  version="1.0">

    <xsl:output encoding="UTF-8" method="text" media-type="text/x-sql"/>

    <xsl:template match="/">
        <xsl:text>-- GENERATED FILE - DO NOT EDIT&#10;&#10;</xsl:text>
        <xsl:value-of select="beans:beans/beans:bean[@id = 'databaseInitializer']/beans:property[@name = 'functions']/dellroad-stuff:sql"/>
    </xsl:template>

</xsl:transform>
