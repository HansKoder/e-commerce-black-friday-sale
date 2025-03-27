import { useEffect, useState } from "react"

export const CountAutomatic = () => {

    const [count, setCount] = useState<number>(0);

    useEffect(() => {
        const interval = setInterval(() => {
            setCount((prevCount) => prevCount + 1);
        }, 1000); // Incrementa cada 1 segundo

        return () => clearInterval(interval); // Limpia el intervalo cuando el componente se desmonta
    }, []);

    return (<>
        <p className="text-7xl">
            Count : {count}
        </p>
    </>)
 
}