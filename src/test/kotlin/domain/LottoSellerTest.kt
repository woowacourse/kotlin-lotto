package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoSellerTest {
    @Test
    fun `입력받은 개수만큼 랜덤 로또를 발급한다`() {
        // given
        val lottoMachine = RandomLottoMachine()
        val lottoSeller = LottoSeller(lottoMachine)

        // when
        val actual = lottoSeller.sellRandomLottos(2)

        // then
        assertThat(actual.size).isEqualTo(2)
    }

    @Test
    fun `입력받은 번호로 수동 로또를 발급한다`() {
        // given
        val lottoMachine = RandomLottoMachine()
        val lottoSeller = LottoSeller(lottoMachine)
        val lottoNumbers = listOf(
            setOf(1, 2, 3, 4, 5, 6),
            setOf(3, 4, 5, 6, 7, 8)
        )

        // when
        val actual = lottoSeller.sellManualLottos(lottoNumbers)

        // then
        assertThat(actual.size).isEqualTo(2)
    }

    @Test
    fun `입력받은 번호로 수동 로또를 발급한다2`() {
        // given
        val lottoMachine = RandomLottoMachine()
        val lottoSeller = LottoSeller(lottoMachine)
        val lottoNumbers = listOf(
            setOf(1, 2, 3, 4, 5, 6),
            setOf(3, 4, 5, 6, 7, 8)
        )

        // when
        val actual = lottoSeller.sellManualLottos(lottoNumbers)

        // then
        assertThat(actual[0].toList()).isEqualTo(listOf(1, 2, 3, 4, 5, 6).map(::LottoNumber))
        assertThat(actual[1].toList()).isEqualTo(listOf(3, 4, 5, 6, 7, 8).map(::LottoNumber))
    }
}
