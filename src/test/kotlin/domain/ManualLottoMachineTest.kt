package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class ManualLottoMachineTest {
    @Test
    fun `수동으로 로또 1장을 생성한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val manualLottoMachine = ManualLottoMachine(listOf(numbers.toSet()))
        assertDoesNotThrow {
            manualLottoMachine.create(1)
        }
    }

    @Test
    fun `입력한 6개의 번호로 로또 1장을 생성한다`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val manualLottoMachine = ManualLottoMachine(listOf(numbers.toSet()))

        // when
        val lotto = manualLottoMachine.create(1)

        // then
        assertThat(lotto[0].numbers).containsAll(
            listOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            )
        )
    }
}
