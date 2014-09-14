
public class Primelist {

	int maxnumber;
	boolean[] numbers;
	void erase(int x)
    {
        for (int i = x * x; i < maxnumber&&x<=Math.sqrt(maxnumber); i += x<<1)//x=x+prime*2,da x+prime gerade und schon bei erase 2 abgehandelt
        {
            numbers[i] = true;
        }
    }
	
	public boolean isNotPrime(int i){
		return numbers[i];
	}
	
	public Multiset<Integer> getPrimefactors(int i){
		int k=2;
		Multiset<Integer> erg=new Multiset<>();
		while (i>1) {
			if (!numbers[k]){
				if (i%k==0){
					i/=k;
					erg.add(k);
				}
				else k++;
			}
			else k++;
		}
		return erg;
	}
	
	public Primelist(int max){
		maxnumber=max;
		numbers = new boolean[maxnumber];
		for (int g = 4; g < maxnumber; g += 2)//erase für 2
        {
            numbers[g] = true;
        }
        for (int i = 3; i < maxnumber; i+=2)
        {
            if (!numbers[i])
            {
                erase(i);
                
            }
        }
	}
}
