package domain.service

import domain.fixture.createLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    @Test
    fun `수동 로또 번호를 입력하면 수동 로또 번호를 담은 수동 로또 객체를 반환한다`() {
        val machine =
            ManualLottoMachine(
                listOf(1, 2, 3, 4, 5, 6),
            )

        val lotto = machine.generate()

        assertThat(lotto).isEqualTo(createLotto())
    }
}
