package models

case class Artist(name: String, country: String)


object Artist {
  val availableArtist = Seq(Artist("Wolfgang Amadeus Mozart", "Austria"),
                             Artist("Ludwig van Beethoven", "Germany"),
                             Artist("Johann Sebastian Bach", "Germany"),
                             Artist("Frédéric François Chopin", "Poland"),
                             Artist("Joseph Haydn", "Austria"),
                             Artist("Antonio Lucio Vivaldi", "Italy"),
                             Artist("Franz Peter Schubert", "Austria"),
                             Artist("Franz Liszt", "Austria"),
                             Artist("Giuseppe Fortunino Francesco Verdi", "Austria"))

  def fetchAll: Seq[Artist] = availableArtist


  def fetchByName(name: String) = availableArtist.filter(_.name.contains(name))

  def fetchByCountry(country: String) = availableArtist.filter(_.country == country)

  def fetchByNameOrCountry(name: String, country: String) =
    availableArtist.filter(a => a.name.contains(name) || a.country == country)

  def fetchByNameAndCountry(name: String, country: String) =
    availableArtist.filter(a => a.name.contains(name) && a.country == country)

}