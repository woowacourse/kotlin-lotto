package lottoTest

import lotto.global.LottoValidator.requireLottoAmount
import lotto.global.LottoValidator.requireValidBonusNum
import lotto.global.LottoValidator.requireValidLotto
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoValidatorTest {
    @Test
    @DisplayName("숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않은 형식입니다\" 라는 메시지를 출력한다\n")
    fun t1() {
        assertThatThrownBy { requireLottoAmount("1dw") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName(" - , 로 번호를 구분하며,숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않는 형식입니다\" 라는 메시지를 출력한다\n")
    fun t2() {
        assertThatThrownBy { requireValidLotto("1,2,3,4,5,2ㅂ34") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("  - 6개의 숫자를 입력받지 않는다면 IllegalArgumentException를 반환하고 \"6개의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_1() {
        assertThatThrownBy { requireValidLotto("1,2,3,4,5,6,7") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("6개의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 하나의 숫자 이상이 1부터 45까지의 범위가 아니라면 IllegalArgumentException를 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_2() {
        assertThatThrownBy { requireValidLotto("1,2,300,4,5,6") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 중복된 숫자를 입력한 경우 IllegalArgumentException을 반환하고 \"중복되지 않은 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t2_3() {
        assertThatThrownBy { requireValidLotto("1,2,3,4,5,5") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("중복되지 않은 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 숫자로 변환할 수 없는 값을 입력받는다면 IllegalArgumentException를 반환하고, \"올바르지 않은 형식입니다\" 라는 메시지를 출력한다\n")
    fun t3() {
        assertThatThrownBy { requireValidBonusNum("11r") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바르지 않은 형식입니다")
    }

    @Test
    @DisplayName("  - 1부터 45 사이의 숫자를 입력받지 않는다면 IllegalArgumentException을 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t3_1() {
        assertThatThrownBy { requireValidBonusNum("2345") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 1부터 45 사이의 숫자를 입력받지 않는다면 IllegalArgumentException을 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t3_1_1() {
        assertThatThrownBy { requireValidBonusNum("-1") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }
}
