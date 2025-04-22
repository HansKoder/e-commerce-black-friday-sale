package cart

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CartSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Cambiá esto si tu backend corre en otro host/puerto
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val bodyJson =
    """{
      |  "customerId": "3307a19e-6430-440a-bbf7-226c04b58be7",
      |  "productId": "0ae5605e-3797-4bb6-a0f7-e7c3f23f367b",
      |  "cant": 3,
      |  "price": 100
      |}""".stripMargin

  val scn = scenario("Guardar un carrito con un producto")
    .exec(
      http("POST /api/v2/cart/items/save")
        .post("/api/v2/cart/items/save") // Ajustar si tu endpoint es distinto
        .body(StringBody(bodyJson)).asJson
        .check(status.is(201))
    )

  setUp(
    scn.inject(atOnceUsers(10)) // 10 usuarios simultáneos
  ).protocols(httpProtocol)
}
