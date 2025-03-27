import React from "react";
import { Count } from "./Count";

export interface ItemProps {
    description: string;
    price: number;
}

export const Item : React.FC<ItemProps> = ({ description, price }) => {

    return (<>
        <article className="p-10 bg-pink-100 m-5">
            <h3>
                {description}
            </h3>
            <p>
                $ {price}
            </p>
            <Count price={price} />
        </article>
    </>);

}