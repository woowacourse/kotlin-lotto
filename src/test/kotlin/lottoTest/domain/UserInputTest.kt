package lottoTest.domain

import lotto.domain.UserInput
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class UserInputTest {
    @Test
    @DisplayName("수동으로 구매한 로또의 금액은 구입 금액을 넘을 수 없다")
    fun t1() {
        assertThatThrownBy {
            UserInput(
                14000,
                15,
                listOf(),
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("수동으로 구매한 금액이 입력 금액보다 큽니다")
    }

    @Test
    @DisplayName("수동으로 구매한 로또의 개수는 최소 0개이다")
    fun t1_1() {
        assertDoesNotThrow {
            UserInput(14500, 0, listOf())
        }
    }

    @Test
    @DisplayName("수동으로 구매한 로또의 개수가 0개일 경우 자동 로또의 개수와 전체 로또의 개수가 같다")
    fun t1_2() {
        assertDoesNotThrow {
            val userInput = UserInput(14500, 0, listOf())
            assertThat(userInput.automaticLottoCount).isEqualTo(userInput.totalLottoCount)
        }
    }

    @Test
    @DisplayName("로또 가격 미만의 값을 입력으로 받을 수 없다")
    fun t2() {
        assertThatThrownBy {
            UserInput(
                900,
                15,
                listOf(),
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("최소 구입 금액은 1000원 이상이여야 합니다")
    }

    @Test
    @DisplayName("자동 로또 구입 개수는 총 구매 가능 개수 - 수동 로또 개수 이다")
    fun t3() {
        val userInput =
            UserInput(
                15000,
                1,
                listOf(listOf(1, 2, 3, 4, 5, 6)),
            )
        assertThat(userInput.automaticLottoCount).isEqualTo(14)
    }

    @Test
    @DisplayName("14500원을 입력받는다면 14000으로 로또 14개를 살 수 있으니 14개를 구매할 수 있다")
    fun t4() {
        val userInput = UserInput(14500, 0, listOf())
        assertThat(userInput.totalLottoCount).isEqualTo(14)
    }

    @Test
    @DisplayName("구매한 수동 로또의 개수만큼 수동 로또를 입력받아야 한다")
    fun t5() {
        assertThatThrownBy {
            UserInput(14500, 11, listOf())
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("구매한 수동 로또의 개수만큼 수동 로또를 입력받아야 합니다")
    }
}
