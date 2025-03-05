package lotto.domain.service

import org.assertj.core.api.Assertions.assertThatIterable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private lateinit var lottosMachine: LottoMachine

    @BeforeEach
    fun setUp() {
        lottosMachine = RandomLottoMachine()
    }

    @Test
    fun `자동 로또 번호는 정렬되어야 한다`() {
        val expect = lottosMachine.generate()
        val actual = expect.numbers.sorted()
        assertThatIterable(expect.numbers).isEqualTo(actual)
    }
}
