import { Item, ItemProps } from "./Item"

export const ShoppingCart = () => {

    const items: ItemProps[] = [
        { "description": "Asus Laptop", "price": 200 },
        { "description": "Asus Laptop", "price": 250 }
    ]

    return (<>
        <section>

            {
                items.map((item, index) => (
                    <Item 
                        key={index}
                        description={item.description}
                        price={item.price}
                    />
                ))
            }
        </section>
        

    </>)

}