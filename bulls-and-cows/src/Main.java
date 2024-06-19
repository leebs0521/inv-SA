import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 컴퓨터 랜덤으로 3자리를 입력 받는 함수
        // "123" or "280"
        String secretNumber = generateSecretNumber();
        int attempts = 0;

        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while (true) {
            attempts++;
            System.out.print(attempts + "번째 시도 : ");
            String guess = scanner.nextLine();

            // 정답 문자열이랑 입력 받은 문자열 비교
            // checkGuess("134", "280") -> "0B0S"
            String result = checkGuess(guess, secretNumber);
            System.out.println(result);

            if (result.equals("3S")) {
                System.out.println(attempts + "번만에 맞히셨습니다.");
                break;
            }
        }

        System.out.println("게임을 종료합니다.");
        scanner.close();
    }

    /*
     *  임의의 3자리 숫자를 생성하여 문자열으로 반환하는 함수
     *  단, 중복된 숫자 불가 "111"->x "122"->x
     *  input: void
     *  output: String
     * */
    private static String generateSecretNumber() {
        Random r = new Random();
        Set<Integer> numSet = new HashSet<Integer>();

        while (numSet.size() < 3) {
            numSet.add(r.nextInt(10)); // 0~9까지
        }

        StringBuilder numStr = new StringBuilder(); //
        for (Integer num : numSet) {
            numStr.append(num); // 숫자 문자열 추가
        }

        return numStr.toString(); // String으로 변환

    }

    /*
     *  임의로 생성한 3자리 숫자와 사용자가 입력한 3자리 숫자를 비교하여 결과 문자열을 생성
     *  input: String, String (입력 문자, 정답 문자)
     *  output: String (비교 결과)
     *  ex) 0S0B / 1S2B
     * */
    private static String checkGuess(String guess, String secretNumber) {

        int sCount = 0;
        int bCount = 0;

        // 숫자의 값과 위치가 모두 일치하면 S, 숫자의 값은 일치하지만 위치가 틀렸으면 B
        for (int i = 0; i < secretNumber.length(); i++) {
            char ch = guess.charAt(i);

            if (ch == secretNumber.charAt(i)) {
                sCount++;
            } else if (secretNumber.indexOf(ch) != -1) {
                bCount++;
            }
        }
        // sCount가 1, 2, 3인 경우 sCount 값만 반환
        if (sCount > 0 && bCount == 0) {
            return sCount + "S";
        }
        return bCount + "B" + sCount + "S";
    }

}