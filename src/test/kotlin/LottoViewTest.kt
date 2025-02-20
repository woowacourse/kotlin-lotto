import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoViewTest {
    @Test
    @DisplayName("로또의 금액을 입력받는다, 로또의 금액은 1000원이며, 최대한 살 수 있는 만큼 구매한다")
    fun t1() {
        assertThat(LottoView.checkLottoAmount("5000")).isEqualTo(5)
    }

    @Test
    @DisplayName("예를 들어 14500원을 입력받는다면 14000으로 로또 14개를 살 수 있으니 14개를 구매한다")
    fun t1_1() {
        assertThat(LottoView.checkLottoAmount("14500")).isEqualTo(14)
    }

    @Test
    @DisplayName("숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않은 형식입니다\" 라는 메시지를 출력한다\n")
    fun t1_2() {
        assertThatThrownBy{ LottoView.checkLottoAmount("1dw") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("당첨 로또를 입력받는다")
    fun t2() {
        assertThat(LottoView.checkValidLotto("1,2,3,4,5,6"))
            .isEqualTo(listOf(1,2,3,4,5,6))
    }

    @Test
    @DisplayName(" - , 로 번호를 구분하며,숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않는 형식입니다\" 라는 메시지를 출력한다\n")
    fun t2_1() {
        assertThatThrownBy{ LottoView.checkValidLotto("1,2,3,4,5,2ㅂ34") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("  - 6개의 숫자를 입력받지 않는다면 IllegalArgumentException를 반환하고 \"6개의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_2() {
        assertThatThrownBy{ LottoView.checkValidLotto("1,2,3,4,5,6,7") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("6개의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 하나의 숫자 이상이 1부터 45까지의 범위가 아니라면 IllegalArgumentException를 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_3() {
        assertThatThrownBy{ LottoView.checkValidLotto("1,2,300,4,5,6") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 중복된 숫자를 입력한 경우 IllegalArgumentException을 반환하고 \"중복되지 않은 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_4() {
        assertThatThrownBy{ LottoView.checkValidLotto("1,2,3,4,5,5") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("중복되지 않은 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("보너스 번호를 입력받는다")
    fun t3() {
        assertThat(LottoView.checkValidBonusNum("6"))
            .isEqualTo(6)
    }
}