package lottoTest

import lotto.LottoService
import lotto.LottoView
import lotto.domain.Lotto
import lotto.domain.LottoNumber
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
    @DisplayName("로또의 금액을 입력받는다, 로또의 금액은 1000원이며, 최대한 살 수 있는 만큼 구매한다")
    fun t1() {
        setInput("5000")
        assertThat(lottoView.getLottoAmount()).isEqualTo(5)
    }

    @Test
    @DisplayName("예를 들어 14500원을 입력받는다면 14000으로 로또 14개를 살 수 있으니 14개를 구매한다")
    fun t1_1() {
        setInput("14500")
        assertThat(lottoView.getLottoAmount()).isEqualTo(14)
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
    @DisplayName("  - 6개의 숫자를 입력받지 않는다면 IllegalArgumentException를 반환하고 \"6개의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_2() {
        setInput("1,2,3,4,5,6,7")
        assertThatThrownBy { lottoView.getWinningLotto() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("6개의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 하나의 숫자 이상이 1부터 45까지의 범위가 아니라면 IllegalArgumentException를 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_3() {
        setInput("1,2,300,4,5,6")
        assertThatThrownBy { lottoView.getWinningLotto() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 중복된 숫자를 입력한 경우 IllegalArgumentException을 반환하고 \"중복되지 않은 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_4() {
        setInput("1,2,3,4,5,5")
        assertThatThrownBy { lottoView.getWinningLotto() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("중복되지 않은 숫자를 입력해주세요")
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
    @DisplayName("  - 1부터 45 사이의 숫자를 입력받지 않는다면 IllegalArgumentException을 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t3_2() {
        setInput("1234")
        assertThatThrownBy { lottoView.getBonusNum() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 1부터 45 사이의 숫자를 입력받지 않는다면 IllegalArgumentException을 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t3_2_1() {
        setInput("-1")
        assertThatThrownBy { lottoView.getBonusNum() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("- 당첨 정보를 입력받으면 정보를 취합하여 다음과 같이 출력한다 (README참조)")
    fun t4() {
        val output = setOut()
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 43, 44, 45).map { LottoNumber.of(it) }),
                Lotto(listOf(11, 12, 13, 14, 15, 16).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = 7
        val rankMap = LottoService().getLottoRankMany(manyLotto, winningLotto, bonus)
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
