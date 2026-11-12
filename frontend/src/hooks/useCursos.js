import { useEffect, useState } from "react";
import { MOCKDATA } from "../data/MockData";

function useCursos() {
    const [cursos, setCursos] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setIsLoading(true);
        const timer = setTimeout(() => {
            setCursos(MOCKDATA);
            setIsLoading(false);
        }, 2000)

        return () => clearTimeout(timer);
    }, [])

    return { cursos, isLoading};
}
export default useCursos;