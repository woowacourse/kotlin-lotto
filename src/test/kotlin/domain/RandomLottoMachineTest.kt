package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class RandomLottoMachineTest {
    @Test
    fun `랜덤 로또 생성 성공`() {
        val randomLottoMachine = RandomLottoMachine()
        assertDoesNotThrow {
            randomLottoMachine.create()
        }
    }

    @Test
    fun `생성한 랜덤 로또의 사이즈는 6`() {
        val randomLottoMachine = RandomLottoMachine()
        val lotto = randomLottoMachine.create()
        assertThat(lotto.size).isEqualTo(6)
    }
}
