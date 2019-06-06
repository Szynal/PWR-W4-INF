<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Super oferta!!!</h1>
                <div>

                </div>
                    <a> Firma o opisie:
                        <xsl:value-of select="OfferType/officeData"/>
                    </a>
                <div>
                    <a style="color:red;"> Opis:
                     Opis wyjazdu:  <xsl:value-of select="OfferType/description"/>
                    </a>
                </div>
                <div>
                    <a>
                     Czas startu: <xsl:value-of select="OfferType/start_date"/>
                    </a>
                </div>
                <div>
                    <a>
                      Koniec:  <xsl:value-of select="OfferType/end_date"/>
                    </a>
                </div>
                <div>
                    <a style="color:red;"> Cena:
                        <xsl:value-of select="OfferType/price"/>
                    </a>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>