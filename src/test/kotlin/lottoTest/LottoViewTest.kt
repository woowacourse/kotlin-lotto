package lottoTest

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.WinningLottoTicket
import lotto.global.UserInputResult
import lotto.view.LottoView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

class LottoViewTest {
    private fun setInput(input: String) {
        val inputStream: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)
    }

    private fun setOut(): ByteArrayOutputStream {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val printStream = PrintStream(byteArrayOutputStream)
        System.setOut(printStream)
        return byteArrayOutputStream
    }

    private val lottoView = LottoView()

    @Test
    @DisplayName("로또의 금액을 입력받는다")
    fun t1() {
        setInput("5000\n")
        val buyAmount = lottoView.getBuyAmount()
        assertThat(buyAmount)
            .isInstanceOf(UserInputResult.Success::class.java)
        assertThat(buyAmount.get()).isEqualTo(5000)
    }

    @Test
    @DisplayName("숫자로 변환할 수 없는 값을 입력받는다면 UserInputResult.Failure 에\"올바르지 않은 형식입니다\"라는 메시지를 담는다")
    fun t1_1() {
        setInput("8g1")
        val userInput = lottoView.getBuyAmount()
        assertThat(userInput).isInstanceOf(UserInputResult.Failure::class.java)
        val userInputFail: UserInputResult.Failure<Int> = userInput as UserInputResult.Failure
        assertThat(userInputFail.errorMessage.msg).contains("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("검증에 실패한 값을 입력받는다면 UserInputResult.Failure 에\"올바르지 않은 형식입니다\"라는 메시지를 담는다")
    fun t1_2() {
        setInput("500")
        val userInput = lottoView.getBuyAmount()
        assertThat(userInput).isInstanceOf(UserInputResult.Failure::class.java)
        val userInputFail: UserInputResult.Failure<Int> = userInput as UserInputResult.Failure
        assertThat(userInputFail.errorMessage.msg).contains("최소 구입 금액은 1000원 이상이여야 합니다")
    }

    @Test
    @DisplayName("수동 로또 개수를 입력받는다")
    fun t2() {
        setInput("1")
        val buyAmount = lottoView.getManualLottoCount(1500)
        assertThat(buyAmount)
            .isInstanceOf(UserInputResult.Success::class.java)
        assertThat(buyAmount.get()).isEqualTo(1)
    }

    @Test
    @DisplayName("숫자로 변환할 수 없는 값을 입력받는다면 UserInputResult.Failure 에\"올바르지 않은 형식입니다\"라는 메시지를 담는다")
    fun t2_1() {
        setInput("8g1")
        val userInput = lottoView.getManualLottoCount(1500)
        assertThat(userInput).isInstanceOf(UserInputResult.Failure::class.java)
        val userInputFail: UserInputResult.Failure<Int> = userInput as UserInputResult.Failure
        assertThat(userInputFail.errorMessage.msg).contains("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("수동 로또 정보를 입력받는다")
    fun t3() {
        setInput("1,2,3,4,5,6\\n1,2,3,4,5,6")
        val buyAmount = lottoView.getManualLotto(2)
        assertThat(buyAmount)
            .isInstanceOf(UserInputResult.Success::class.java)
        assertThat(buyAmount.get()).isEqualTo(
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
            ),
        )
    }

    @Test
    @DisplayName("당첨 로또를 입력받는다")
    fun t4() {
        setInput("1,2,3,4,5,6")
        val userInput = lottoView.getWinningLotto()
        assertThat(userInput.get())
            .isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    @DisplayName(" - , 로 번호를 구분하며,숫자로 변환할 수 없는 값을 입력받는다면 \"올바르지 않는 형식입니다\" 라는 메시지를 출력하고 프로그램을 종료한다\n")
    fun t4_1() {
        setInput("1,2,3,4,5,2ㅂ34")
        val userInput = lottoView.getWinningLotto()
        val userInputFail: UserInputResult.Failure<List<Int>> = userInput as UserInputResult.Failure
        assertThat(userInputFail.errorMessage.msg).contains("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("보너스 번호를 입력받는다")
    fun t5() {
        setInput("6")
        assertThat(lottoView.getBonusNum().get())
            .isEqualTo(6)
    }

    @Test
    @DisplayName("  - 숫자로 변환할 수 없는 값을 입력받는다면, \"올바르지 않은 형식입니다\" 라는 메시지를 출력가혹 프로그램을 종료한다\n")
    fun t3_1() {
        setInput("11r")
        val userInput = lottoView.getBonusNum()
        val userInputFail: UserInputResult.Failure<Int> = userInput as UserInputResult.Failure
        assertThat(userInputFail.errorMessage.msg).contains("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("- 당첨 정보를 입력받으면 정보를 취합하여 다음과 같이 출력한다 (README참조)")
    fun t6() {
        val output = setOut()
        val manyLotto =
            listOf(
                Lotto.of(1, 2, 3, 43, 44, 45),
                Lotto.of(11, 12, 13, 14, 15, 16),
            )
        val winningLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.of(7)
        val rankMap = WinningLottoTicket(winningLotto, bonus).findLottoRanks(manyLotto)
        lottoView.printResult(rankMap)
        assertThat(output.toString()).contains(
            """
            당첨 통계
            ---------
            3개 일치 (5000원)- 1개
            4개 일치 (50000원)- 0개
            5개 일치 (1500000원)- 0개
            5개 일치, 보너스 볼 일치(30000000원) - 0개
            6개 일치 (2000000000원)- 0개
            총 수익률은 2.50입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
            """.trimIndent(),
        )
    }
}
