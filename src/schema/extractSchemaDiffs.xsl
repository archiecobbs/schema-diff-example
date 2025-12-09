<?xml version="1.0" encoding="UTF-8"?>

<xsl:transform
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:log="http://www.liquibase.org/xml/ns/dbchangelog"
  xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
    http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd
    http://www.liquibase.org/xml/ns/dbchangelog     http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd"
  version="1.0">

    <xsl:output encoding="UTF-8" method="text" media-type="text/plain"/>

    <xsl:template match="/">
        <xsl:variable name="num" select="count(log:databaseChangeLog/log:changeSet)"/>
        <xsl:if test="$num &gt; 0">
            <xsl:value-of select="concat('*** Found ', $num, ' schema difference(s) ***&#10;')"/>
        </xsl:if>
    </xsl:template>

</xsl:transform>
