package model

class Lotto(val purchasePrice: Int) {
    fun getLottoCount(): Int{
        return purchasePrice/1000
    }

}
