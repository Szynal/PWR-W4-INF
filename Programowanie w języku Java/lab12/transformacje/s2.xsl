<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Super offer!!!</h1>
                <div>
                    <a>
                        Offer by company: <xsl:value-of select="OfferType/officeData"/>
                    </a>
                </div>
                <div>
                    <a style="color:blue;"> Opis:
                        What is in price: <xsl:value-of select="OfferType/description"/>
                    </a>
                </div>
                <div>
                    <a>
                       Start  <xsl:value-of select="OfferType/start_date"/>
                    </a>
                </div>
                <div>
                    <a>
                       End time <xsl:value-of select="OfferType/end_date"/>
                    </a>
                </div>
                <div>
                    <a style="color:blue;"> Cena:
                       Price: <xsl:value-of select="OfferType/price"/>
                    </a>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>