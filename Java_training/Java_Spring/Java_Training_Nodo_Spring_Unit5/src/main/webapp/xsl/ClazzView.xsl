<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <div align="center">
                    <xsl:apply-templates/>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="clazz">
        <table border="1" width="250">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Age</td>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="data">
                    <tr>
                        <td>
                            <xsl:value-of select="data/id"/>
                        </td>
                        <td>
                            <xsl:value-of select="data/name"/>
                        </td>
                        <td>
                            <xsl:value-of select="data/age"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>
</xsl:stylesheet>