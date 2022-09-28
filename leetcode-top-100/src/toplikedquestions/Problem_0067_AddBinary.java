package toplikedquestions;

public class Problem_0067_AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;  // 充当sum和carry的角色
        int size = Math.max(a.length(), b.length());
        for (int i = 0; i < size; i++) {
            carry += i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;  // 倒叙进行
            carry += i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;
            sb.append(carry % 2);
            carry >>= 1;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
