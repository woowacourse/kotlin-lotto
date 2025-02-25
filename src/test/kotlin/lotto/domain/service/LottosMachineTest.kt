package lotto.domain.service

import lotto.domain.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIterable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottosMachineTest {
    private lateinit var lottosMachine: LottosMachine

    @BeforeEach
    fun setUp() {
        lottosMachine = LottosMachine(LottoMachineImpl())
    }

    @Test
    fun `로또 번호는 정렬되어야 한다`() {
        val expect = lottosMachine.generate(8)
        val actual =
            expect.map {
                val sortedLottoNumber = it.numbers.sorted()
                Lotto(sortedLottoNumber.toSet())
            }

        assertThatIterable(expect).isEqualTo(actual)
    }

    @Test
    fun `로또 번호는 요청 개수만큼 생성 되어야 한다`() {
        val expect = lottosMachine.generate(8)
        val actual = 8

        assertThat(expect.size).isEqualTo(actual)
    }
}
