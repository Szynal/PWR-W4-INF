package testy_fitnesse_fixture;
import Warstwa_biznesowa.TInterfejs;
import fit.Fixture;

public class SetUp extends Fixture{
 static TInterfejs aplikacja;
 public SetUp() {
 aplikacja = new TInterfejs();
 }
}
