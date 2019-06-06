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

				</div>
				<a>
					Company:
					<a style="color:gray;">
						<xsl:value-of select="OfferType/officeData" />
					</a>
				</a>
				<div>
					Description:
					<a style="color:gray;">
						<xsl:value-of select="OfferType/description" />
					</a>
				</div>
				<div>
					<a>
						Start time:
						<a style="color:gray;">
							<xsl:value-of select="OfferType/start_date" />
						</a>
					</a>
				</div>
				<div>
					<a>
						End:
						<a style="color:gray;">
							<xsl:value-of select="OfferType/end_date" />
						</a>
					</a>
				</div>
				<div>
					Price:
					<a style="color:gray;">
						<xsl:value-of select="OfferType/price" />
					</a>
				</div>
				<div>
					Format: format01
				</div>


				<center>
					<p style="font-size:100px">&#128540;</p>
				</center>


			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>