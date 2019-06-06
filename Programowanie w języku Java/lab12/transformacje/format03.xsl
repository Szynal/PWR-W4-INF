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
					<a face="verdana" style="color:red;">
						Company:
						<xsl:value-of select="OfferType/officeData" />
					</a>
				</div>
				<div>
					<a face="verdana" style="color:blue;">
						Description:
						<xsl:value-of select="OfferType/description" />
					</a>
				</div>
				<div>
					<a face="verdana" style="color:red;">
						Start day:
						<xsl:value-of select="OfferType/start_date" />
					</a>
				</div>
				<div>
					<a face="verdana" style="color:blue;">
						End time:
						<xsl:value-of select="OfferType/end_date" />
					</a>
				</div>
				<div>
					<a face="verdana" style="color:red;">
						Price:
						<xsl:value-of select="OfferType/price" />
					</a>
				</div>
				<center>
					<span style='font-size:100px;'>&#129297;</span>
				</center>

			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>