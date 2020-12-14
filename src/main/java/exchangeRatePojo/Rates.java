package exchangeRatePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hsbc.api.utilities.BasePojo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates extends BasePojo {
	private String GBP;
	
    private String CHF;

    private String HRK;

    private String MXN;

    private String ZAR;

    private String INR;

    private String THB;

    private String CNY;

    private String AUD;

    private String ILS;

    private String KRW;

    private String JPY;

    private String PLN;    

    private String IDR;

    private String HUF;

    private String PHP;

    private String TRY;

    private String RUB;

    private String HKD;

    private String ISK;

    private String DKK;

    private String MYR;

    private String CAD;

    private String USD;

    private String BGN;

    private String NOK;

    private String RON;

    private String SGD;

    private String CZK;

    private String SEK;

    private String NZD;

    private String BRL;

    public String getCHF ()
    {
        return CHF;
    }

    public void setCHF (String CHF)
    {
        this.CHF = CHF;
    }

    public String getHRK ()
    {
        return HRK;
    }

    public void setHRK (String HRK)
    {
        this.HRK = HRK;
    }

    public String getMXN ()
    {
        return MXN;
    }

    public void setMXN (String MXN)
    {
        this.MXN = MXN;
    }

    public String getZAR ()
    {
        return ZAR;
    }

    public void setZAR (String ZAR)
    {
        this.ZAR = ZAR;
    }

    public String getINR ()
    {
        return INR;
    }

    public void setINR (String INR)
    {
        this.INR = INR;
    }

    public String getTHB ()
    {
        return THB;
    }

    public void setTHB (String THB)
    {
        this.THB = THB;
    }

    public String getCNY ()
    {
        return CNY;
    }

    public void setCNY (String CNY)
    {
        this.CNY = CNY;
    }

    public String getAUD ()
    {
        return AUD;
    }

    public void setAUD (String AUD)
    {
        this.AUD = AUD;
    }

    public String getILS ()
    {
        return ILS;
    }

    public void setILS (String ILS)
    {
        this.ILS = ILS;
    }

    public String getKRW ()
    {
        return KRW;
    }

    public void setKRW (String KRW)
    {
        this.KRW = KRW;
    }

    public String getJPY ()
    {
        return JPY;
    }

    public void setJPY (String JPY)
    {
        this.JPY = JPY;
    }

    public String getPLN ()
    {
        return PLN;
    }

    public void setPLN (String PLN)
    {
        this.PLN = PLN;
    }

    public String getGBP ()
    {
        return GBP;
    }

    public void setGBP (String GBP)
    {
        this.GBP = GBP;
    }

    public String getIDR ()
    {
        return IDR;
    }

    public void setIDR (String IDR)
    {
        this.IDR = IDR;
    }

    public String getHUF ()
    {
        return HUF;
    }

    public void setHUF (String HUF)
    {
        this.HUF = HUF;
    }

    public String getPHP ()
    {
        return PHP;
    }

    public void setPHP (String PHP)
    {
        this.PHP = PHP;
    }

    public String getTRY ()
    {
        return TRY;
    }

    public void setTRY (String TRY)
    {
        this.TRY = TRY;
    }

    public String getRUB ()
    {
        return RUB;
    }

    public void setRUB (String RUB)
    {
        this.RUB = RUB;
    }

    public String getHKD ()
    {
        return HKD;
    }

    public void setHKD (String HKD)
    {
        this.HKD = HKD;
    }

    public String getISK ()
    {
        return ISK;
    }

    public void setISK (String ISK)
    {
        this.ISK = ISK;
    }

    public String getDKK ()
    {
        return DKK;
    }

    public void setDKK (String DKK)
    {
        this.DKK = DKK;
    }

    public String getMYR ()
    {
        return MYR;
    }

    public void setMYR (String MYR)
    {
        this.MYR = MYR;
    }

    public String getCAD ()
    {
        return CAD;
    }

    public void setCAD (String CAD)
    {
        this.CAD = CAD;
    }

    public String getUSD ()
    {
        return USD;
    }

    public void setUSD (String USD)
    {
        this.USD = USD;
    }

    public String getBGN ()
    {
        return BGN;
    }

    public void setBGN (String BGN)
    {
        this.BGN = BGN;
    }

    public String getNOK ()
    {
        return NOK;
    }

    public void setNOK (String NOK)
    {
        this.NOK = NOK;
    }

    public String getRON ()
    {
        return RON;
    }

    public void setRON (String RON)
    {
        this.RON = RON;
    }

    public String getSGD ()
    {
        return SGD;
    }

    public void setSGD (String SGD)
    {
        this.SGD = SGD;
    }

    public String getCZK ()
    {
        return CZK;
    }

    public void setCZK (String CZK)
    {
        this.CZK = CZK;
    }

    public String getSEK ()
    {
        return SEK;
    }

    public void setSEK (String SEK)
    {
        this.SEK = SEK;
    }

    public String getNZD ()
    {
        return NZD;
    }

    public void setNZD (String NZD)
    {
        this.NZD = NZD;
    }

    public String getBRL ()
    {
        return BRL;
    }

    public void setBRL (String BRL)
    {
        this.BRL = BRL;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CHF = "+CHF+", HRK = "+HRK+", MXN = "+MXN+", ZAR = "+ZAR+", INR = "+INR+", THB = "+THB+", CNY = "+CNY+", AUD = "+AUD+", ILS = "+ILS+", KRW = "+KRW+", JPY = "+JPY+", PLN = "+PLN+", GBP = "+GBP+", IDR = "+IDR+", HUF = "+HUF+", PHP = "+PHP+", TRY = "+TRY+", RUB = "+RUB+", HKD = "+HKD+", ISK = "+ISK+", DKK = "+DKK+", MYR = "+MYR+", CAD = "+CAD+", USD = "+USD+", BGN = "+BGN+", NOK = "+NOK+", RON = "+RON+", SGD = "+SGD+", CZK = "+CZK+", SEK = "+SEK+", NZD = "+NZD+", BRL = "+BRL+"]";
    }
}
