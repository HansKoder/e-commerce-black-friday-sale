import { useEffect, useState } from "react";

export interface countProps {
    price: number;
}

export const Count: React.FC<countProps> = ({price}) => {

    const [count, setCount] = useState<number>(1);
    const [total, setTotal] = useState<number>(price);

    useEffect(() => {
        const newTotal = price * count;
        console.log(`[INFO] set new Total ${newTotal}`);       
        setTotal(newTotal);
    }, [count, price])

    const calculateTotal = (event: React.ChangeEvent<HTMLInputElement>) => {        
        const newCount = Math.max(0, Number(event.target.value));
        console.log(`[INFO] set new Count ${newCount}`);       
        setCount(newCount);
        event.target.value = String(newCount);
    }

    return (<>
        <div>
            <label >
                Count
            </label>
            <input type="number" placeholder="Count of items.." 
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
            value={count}
            onChange={calculateTotal} 
            pattern="[0-9]*"
            inputMode="numeric"/>
        </div>        

        <p className="">Total $: {total}</p>
    </>)

}