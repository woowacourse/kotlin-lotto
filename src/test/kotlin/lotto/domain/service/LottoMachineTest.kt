package lotto.domain.service

import org.assertj.core.api.Assertions.assertThatIterable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private lateinit var lottosMachine: LottoMachine

    @BeforeEach
    fun setUp() {
        lottosMachine = LottoMachineImpl()
    }

    @Test
    fun `자동 로또 번호는 정렬되어야 한다`() {
        val expect = lottosMachine.generateRandomLottoNumbers()
        val actual = expect.numbers.sorted()
        assertThatIterable(expect.numbers).isEqualTo(actual)
    }

    @Test
    fun `수동 로또 번호는 정렬되어야 한다`() {
        val numbers = listOf(1, 7, 6, 4, 8, 9)
        val expect = lottosMachine.generateManualLottoNumbers(numbers)

        assertThatIterable(expect.numbers.toList()).containsExactly(1, 4, 6, 7, 8, 9)
    }

    @Test
    fun `수동 로또는 요청 받은 숫자로 생성 되어야 한다`() {
        val numbers = listOf(1, 7, 6, 4, 8, 9)
        val expect = lottosMachine.generateManualLottoNumbers(numbers)

        assertThatIterable(expect.numbers.toList()).containsExactlyInAnyOrder(1, 4, 6, 7, 8, 9)
    }
}
