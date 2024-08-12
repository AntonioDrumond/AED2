public class palindromo{
	public static boolean pali(String s){
		int i = 0,
			f = s.length()-1;
		boolean result = true;
		while(result && i!=f && i<s.length()/2){
			if(s.charAt(i) != s.charAt(f)) result = false;
			i++;
			f--;
		}
		return result;
	}

	public static void main(String[] args){
		System.out.println(pali("ana"));
		System.out.println(pali("anna"));
		System.out.println(pali("oirato otario"));
		System.out.println(pali("oiratootario"));
	}
}
