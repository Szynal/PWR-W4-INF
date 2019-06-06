<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html>
			<body>
				<center>
					<h1>Travel agency offer</h1>
				</center>
				<div>
					Company:
					<a style="color:blue;">
						<xsl:value-of select="OfferType/officeData" />
					</a>
				</div>
				<div>
					Description:
					<a style="color:blue;">

						<xsl:value-of select="OfferType/description" />
					</a>
				</div>
				<div>
					Start day:
					<a style="color:blue;">
						<xsl:value-of select="OfferType/start_date" />
					</a>
				</div>
				<div>
					End time:
					<a style="color:blue;">
						<xsl:value-of select="OfferType/end_date" />
					</a>
				</div>
				<div>
					Price:
					<a style="color:blue;">
						<xsl:value-of select="OfferType/price" />
					</a>
				</div>

				<center>
					<span style='font-size:100px;'>&#128520;</span>
				</center>

			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>