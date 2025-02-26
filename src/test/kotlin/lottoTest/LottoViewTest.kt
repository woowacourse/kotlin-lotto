package lottoTest

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.UserInput
import lotto.domain.WinningLottoTicket
import lotto.service.LottoRankFinder
import lotto.view.LottoView
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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
    @DisplayName("로또의 금액, 수동 로또 개수, 수동 로또 정보를 입력받는다")
    fun t1() {
        setInput("5000\n1\n1,2,3,4,5,6")
        assertThat(lottoView.getLottoAmount()).isEqualTo(
            UserInput(5000, 1, listOf(listOf(1, 2, 3, 4, 5, 6))),
        )
    }

    @Test
    @DisplayName("숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않은 형식입니다\" 라는 메시지를 출력한다\n")
    fun t1_2() {
        setInput("8g1")
        assertThatThrownBy { lottoView.getLottoAmount() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("당첨 로또를 입력받는다")
    fun t2() {
        setInput("1,2,3,4,5,6")
        assertThat(lottoView.getWinningLotto())
            .isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    @DisplayName(" - , 로 번호를 구분하며,숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않는 형식입니다\" 라는 메시지를 출력한다\n")
    fun t2_1() {
        setInput("1,2,3,4,5,2ㅂ34")
        assertThatThrownBy { lottoView.getWinningLotto() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("보너스 번호를 입력받는다")
    fun t3() {
        setInput("6")
        assertThat(lottoView.getBonusNum())
            .isEqualTo(6)
    }

    @Test
    @DisplayName("  - 숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않은 형식입니다\" 라는 메시지를 출력한다\n")
    fun t3_1() {
        setInput("11r")
        assertThatThrownBy { lottoView.getBonusNum() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("- 당첨 정보를 입력받으면 정보를 취합하여 다음과 같이 출력한다 (README참조)")
    fun t4() {
        val output = setOut()
        val manyLotto =
            listOf(
                Lotto.of(1, 2, 3, 43, 44, 45),
                Lotto.of(11, 12, 13, 14, 15, 16),
            )
        val winningLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.of(7)
        val rankMap = LottoRankFinder().findLottoRanks(manyLotto, WinningLottoTicket(winningLotto, bonus))
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
